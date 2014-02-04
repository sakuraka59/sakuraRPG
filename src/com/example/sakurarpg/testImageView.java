package com.example.sakurarpg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

class testImageView extends Thread {
	// �V�X�e���S�̂̕ϐ�
	private SurfaceHolder		mHolder;							// �T�[�t�F�C�X�z���_�[

	// �X���b�h�֌W
	private static final int	CYCLE_TIME	= 50;					// �T�C�N���^�C��=50ms

	// �摜�֌W
	private Bitmap				imgVdGame;							// ���z��ʂ�r�b�g�}�b�v�������C���[�W(Game)
	private Bitmap				imgEnemy;							// �G�摜
	private Rect				rctEnemyOriginalSize;				// �G�̃I���W�i���摜�T�C�Y

	// ��ʊ֌W
	private static final int	VD_WIDTH	= 480;					// ���z��ʁi���j
	private static final int	VD_HEIGHT	= 854;					// ���z��ʁi�����j

	// �e��t���O
	private boolean				bRunning	= false;				// ���C�����[�v����t���O�i�O���A�N�Z�X�j

	testOtherObject hoge;
	
/*********************** �O������Ă΂�郁�\�b�h ***********************/
	// �R���X�g���N�^
	public testImageView(SurfaceHolder surfaceHolder, Context context) {
		this.mHolder = surfaceHolder;

		// ���\�[�X�̃C���X�^���X��擾
		Resources r = context.getResources();

		// �e�摜��C���X�^���X��
		imgVdGame = Bitmap.createBitmap(VD_WIDTH, VD_HEIGHT, Bitmap.Config.ARGB_8888);
		imgEnemy = BitmapFactory.decodeResource(r, R.drawable.light);

		// �G�摜�̃I���W�i���T�C�Y��擾
		rctEnemyOriginalSize = new Rect(0, 0, imgEnemy.getWidth(), imgEnemy.getHeight());
		
		hoge = new testOtherObject(context);
		
	}

	// ���C�����[�v�̓��싖�ݒ�
	public void enableRunning(boolean flag) {
		this.bRunning = flag;		// ���C�����[�v���싖��
	}

/*********************** ������ ***********************/
// ***** �G�̏��� *****
	// �G�̓���
	public Rect		rctOriginalCurrentSize;	// �I���W�i���T�C�Y�̓G�摜�̌��݂�nCount�ɉ������T�C�Y
	public Rect		rctCurrentArea;			// ���z��ʏ�̌��݂̐�L��W
	public int		nCount = 1;				// bAlive=true, bAppearance=true�̂Ƃ��o�����̓���i�O��MAX�j
											// balive=true, bAppearance=false�̂Ƃ� �����ގ��̓���iMAX���O�j
											// bAlive=false�̂Ƃ��͂��ꂽ�Ƃ��̓���i�O�`MAX�F���ꏈ���j
	public int		nAddition = +1;			// nCount�̑���

	private static final int	ENEMY_COUNT_MAX = 10;				// �G�̍ő哮���

	private void moveEnemy() {
		if(0 >= nCount) {
			// �����݂�����̂Ő܂�Ԃ��ďo��������
			nAddition = +1;
		} else if(0 < nAddition && ENEMY_COUNT_MAX <= nCount) {
			// �ő�T�C�Y�܂ŏo�������ꍇ
			// ������}�C�i�X�ɂ���
			nAddition = -1;
		} else {
			;	// ��ɉ����Ȃ�
		}
		// nCount���Z����
		nCount += nAddition;

		// �I���W�i���T�C�Y�̓G�摜�̃T�C�Y��X�V
		// nCount���獂����Z�o
		int height = (int)(rctEnemyOriginalSize.bottom * nCount / ENEMY_COUNT_MAX);
		rctOriginalCurrentSize = new Rect(rctEnemyOriginalSize.left, rctEnemyOriginalSize.top,
				rctEnemyOriginalSize.right, height);

		// ���݂̓G�̍�W��X�V
		// �܂���nCount���獂����Z�o
		height = (int)(rctEnemyOriginalSize.bottom * nCount / ENEMY_COUNT_MAX);
		rctCurrentArea = new Rect(rctEnemyOriginalSize.left, rctEnemyOriginalSize.bottom - height,
				rctEnemyOriginalSize.right, rctEnemyOriginalSize.bottom);
	}

// ***** ��ʊ֌W�̏��� *****
	// ���z��ʁi�r�b�g�}�b�v�j�𐶐�����
	private void genVirtualDisplay() {
		// ���z��ʂ̉��n�𐶐�
		Canvas canvas = new Canvas(imgVdGame);

		// Paint��C���X�^���X��
		Paint paint =new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));

		// �w�i��h��Ԃ�
		canvas.drawColor(Color.argb(255, 0, 0, 32));

		// �G�̓\��t��
		canvas.drawBitmap(imgEnemy, rctOriginalCurrentSize, rctCurrentArea, paint);
	}

// ***** ��ʊ֌W�̏��� *****
	// bmp���ʂɓ\��t��
	private void doDraw(Canvas canvas) {
		// Paint��C���X�^���X��
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));

		// �w�i��h��Ԃ�
		canvas.drawColor(Color.argb(255, 0, 0, 32));

		// ��������bmp�i���z��ʁj���ʂɕ\��
		canvas.drawBitmap(imgVdGame, 0, 0, paint);
	}

// ***** ���C������ *****
	private void main() {
		// �G���X�V
		moveEnemy();

		// ���z��ʐ���
		genVirtualDisplay();
	}

/*********************** ���C�����[�v ***********************/
	// �X���b�h��N������ƌĂ΂��
	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		long now = 0;
		long start_time = 0;
		long loop_time = 0;
		long sleep_time = 0;
		while(bRunning) {
			// ���[�v�J�n������L�^
			start_time = System.currentTimeMillis();

			// ���C������
			main();

			Canvas canvas = null;
			try {
				// �`���J�n��錾
				canvas = mHolder.lockCanvas(null);

				// ��ʂ�\��
				doDraw(canvas);

				hoge.doDraw(canvas);
				{	// for Debug
					Paint paint=new Paint();
					paint.setAntiAlias(true);

					paint.setTextSize(36);
					paint.setColor(Color.YELLOW);
					int j=2;
					canvas.drawText("loop_time="+loop_time, 0, 40*j, paint); j++;
					canvas.drawText("sleep_time="+sleep_time, 0, 40*j, paint); j++;
				}

			} catch(Exception e) {
			} finally {
				if (null != canvas) {
					//�`���I��
					mHolder.unlockCanvasAndPost(canvas);
				}
			}

			// ���[�v����莞�Ԃ̊Ԋu�ŉ�邽�߂̏���
			now = System.currentTimeMillis();
			loop_time = now - lastTime;		// ���[�v�P��̎��� for Debug
			sleep_time = CYCLE_TIME - (now - start_time);
			lastTime = now;

			//�X���[�v
			try {
				Thread.sleep(sleep_time);
			} catch (Exception e) {
			}
		}
	}
}
